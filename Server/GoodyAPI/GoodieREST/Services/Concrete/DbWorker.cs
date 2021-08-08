using System;
using Npgsql;
using GoodyAPI.Models;
using GoodyAPI.Services.Interfaces;
using Microsoft.AspNetCore.Mvc.Diagnostics;
using Microsoft.Extensions.Logging;

namespace GoodyAPI.Services
{
    public class DbWorker : IDbWorkerService
    {
        private readonly ILogger<DbWorker> _logger;
        private const string ConnectionString = "Host=borpa;Username=postgres;Password=pZQ5ylHTjcrNqTVntvDx;Database=goodielist";
        
        public DbWorker(ILogger<DbWorker> logger)
        {
            _logger = logger;
        }

        public bool CreateUser(RegisterModel registerModel)
        {
            using var conn = new NpgsqlConnection(ConnectionString);
            conn.Open();
            try
            {
                using var command = new NpgsqlCommand("SELECT UserRegister(@username, @passwordHash)", conn);
                command.Parameters.AddWithValue("username", registerModel.Username);
                command.Parameters.AddWithValue("passwordHash", HashService.GetSha256HashString(registerModel.Password));
                var reader = command.ExecuteReader();
                if (reader.Read())
                {
                    return reader.GetBoolean(0);
                }
            }
            catch (Exception ex)
            {
                _logger.Log(LogLevel.Error,$"Caught db exception: {ex.Message}");
            }

            return false;
        }

        public UserData FetchUserData(string username)
        {
            using var conn = new NpgsqlConnection(ConnectionString);
            conn.Open();
            try
            {
                using var command = new NpgsqlCommand("SELECT * FROM Users WHERE username = @username", conn);
                command.Parameters.AddWithValue("username", username);
                var reader = command.ExecuteReader();
                if (reader.Read())
                {
                    var userData = new UserData();
                
                    userData.Username = reader.GetString(0);
                    userData.PasswordHash = reader.GetString(1);
                    userData.Created = reader.GetDateTime(2);
                        
                    return userData;
                }
            }
            catch (Exception ex)
            {
                _logger.Log(LogLevel.Error,$"Caught db exception: {ex.Message}");
            }

            return null;
        }


        public RecipeModel FetchRecipe(string username, int identifier)
        {            
            using var conn = new NpgsqlConnection(ConnectionString);
            conn.Open();
            try
            {
                using var command = new NpgsqlCommand("SELECT * FROM Recipes r WHERE (r.Username, r.ID) = ($username, $id);", conn);
                command.Parameters.AddWithValue("username", username);
                command.Parameters.AddWithValue("identifier", identifier);
                var reader = command.ExecuteReader();
                if (reader.Read())
                {
                    var recipe = new RecipeModel();

                    recipe.Username = reader.GetString(0);
                    recipe.Id = reader.GetInt32(1);
                    recipe.Contents = reader.GetString(2);

                    return recipe;
                }
            }
            catch (Exception ex)
            {
                _logger.Log(LogLevel.Error,$"Caught db exception: {ex.Message}");
            }

            return null;
        }
    }
}
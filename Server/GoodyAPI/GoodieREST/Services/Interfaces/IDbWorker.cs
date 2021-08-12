using System;
using System.Collections.Generic;
using GoodyAPI.Models;

namespace GoodyAPI.Services.Interfaces
{
    public interface IDbWorkerService
    {
        public bool CreateUser(RegisterModel registerModel);
        
        public UserData FetchUserData(string username);

        public List<RecipeModel> FetchUserRecipes(string username);

        public RecipeModel FetchRecipeById(Guid identifier);

        public List<RecipeModel> FetchRecentRecipes(int count);
    }
}
using GoodyAPI.Models;

namespace GoodyAPI.Services.Interfaces
{
    public interface IDbWorkerService
    {
        public bool CreateUser(RegisterModel registerModel);
        
        public UserData FetchUserData(string username);

        public RecipeModel FetchRecipe(string username, int identifier);
    }
}
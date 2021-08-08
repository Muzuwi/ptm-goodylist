using GoodyAPI.Models; 

namespace GoodyAPI.Services.Interfaces
{
    public interface IAuthManager
    {
        public UserToken Authenticate(LoginModel loginModel); 
    }
}
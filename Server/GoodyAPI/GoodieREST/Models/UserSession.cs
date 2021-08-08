using System.ComponentModel.DataAnnotations;

namespace GoodyAPI.Models
{
    public class UserSession
    {
        [Required]
        public UserToken Token { get; set; }
        
        [Required]
        public string Username { get; set; }
    }
}
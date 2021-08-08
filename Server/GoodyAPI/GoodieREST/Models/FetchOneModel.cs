using System.ComponentModel.DataAnnotations;

namespace GoodyAPI.Models
{
    public class FetchOneModel
    {
        [Required]
        public UserToken Token { get; set; }
        
        [Required]
        public int RecipeId { get; set; }
    }
}
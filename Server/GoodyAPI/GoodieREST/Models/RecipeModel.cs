using System.ComponentModel.DataAnnotations;

namespace GoodyAPI.Models
{
    public class RecipeModel
    {
        [Required]
        public string Username { get; set; }
        
        [Required]
        public int Id { get; set; }
        
        [Required]
        public string Contents { get; set; }
    }
}
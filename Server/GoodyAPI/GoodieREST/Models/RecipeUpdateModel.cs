using System;
using System.ComponentModel.DataAnnotations;

namespace GoodyAPI.Models
{
    public class RecipeUpdateModel
    {
        [Required]
        public long Created { get; set; }
        
        [Required]
        public string Json { get; set; }
    }
}
using System;
using System.ComponentModel.DataAnnotations;

namespace GoodyAPI.Models
{
    public class RecipeModel
    {
        [Required]
        public Guid Id { get; set; }

        [Required]
        public string Username { get; set; }

        [Required]
        public string Json { get; set; }
    }
}
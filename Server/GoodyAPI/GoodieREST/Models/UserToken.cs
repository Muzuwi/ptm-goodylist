using System;
using System.ComponentModel.DataAnnotations;

namespace GoodyAPI.Models
{
    public class UserToken
    {
        [Required]
        public Guid Token { get; set; }
        
        [Required]
        public DateTime Created { get; set; }
        
        [Required]
        public DateTime Expires { get; set; }
    }
}
using System;
using System.ComponentModel.DataAnnotations;
using GoodyAPI.Models;
using GoodyAPI.Services.Concrete;
using GoodyAPI.Services.Interfaces;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;

namespace GoodyAPI.Controllers
{
    [ApiController]
    [Route("api/v1/recipes/")]
    public class RecipeController : ControllerBase
    {
        private readonly ILogger<RecipeController> _logger;
        private readonly IDbWorkerService _dbWorkerService;

        public RecipeController(ILogger<RecipeController> logger, IDbWorkerService worker)
        {
            _logger = logger; 
            _dbWorkerService = worker;
        }

        [HttpGet]
        [Route("id/{token}")]
        public ActionResult GetById([FromHeader(Name = "X-User-Token")] Guid userToken, Guid recipeId)
        {
            _logger.Log(LogLevel.Information,userToken.ToString());
            var session = SessionManager.GetSessionFor(userToken);
            if (session == null)
            {
                return StatusCode(403);
            }

            var recipe = _dbWorkerService.FetchRecipeById(recipeId);
            if (recipe != null)
            {
                return Ok(recipe);
            }
            
            return NotFound();
        }

        [HttpGet]
        [Route("new")]
        public ActionResult GetByNew([FromHeader(Name = "X-User-Token")] Guid userToken)
        {
            _logger.Log(LogLevel.Information,userToken.ToString());
            var session = SessionManager.GetSessionFor(userToken);
            if (session == null)
            {
                return StatusCode(403);
            }

            var recipes = _dbWorkerService.FetchRecentRecipes(100);
            if (recipes != null)
            {
                _logger.Log(LogLevel.Information, $"Returning {recipes.Count} elements");
                return Ok(recipes);
            }

            return BadRequest();
        }
        
        [HttpGet]
        [Route("user")]
        public ActionResult GetByUser([FromHeader(Name = "X-User-Token")] Guid userToken)
        {
            _logger.Log(LogLevel.Information,userToken.ToString());

            return Ok();
        }
        
    }
}
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
        [Route("id/{token:guid}")]
        public ActionResult GetById([FromHeader(Name = "X-User-Token")] Guid userToken, Guid token)
        {
            var session = SessionManager.GetSessionFor(userToken);
            if (session == null)
            {
                return StatusCode(403);
            }

            var recipe = _dbWorkerService.FetchRecipeById(token);
            if (recipe != null)
            {
                return Ok(recipe);
            }
            
            return NotFound();
        }

        [HttpPost]
        [Route("id/{recipeId:guid}")]
        public ActionResult PostById([FromHeader(Name = "X-User-Token")] Guid userToken, Guid recipeId, [FromBody] RecipeUpdateModel updateModel)
        {
            var session = SessionManager.GetSessionFor(userToken);
            if (session == null)
            {
                return StatusCode(403);
            }

            var ret = _dbWorkerService.InsertRecipeById(recipeId, session.Username, updateModel.Created, updateModel.Json);
            if (!ret)
            {
                return BadRequest();    
            }

            return Ok();
        }

        [HttpDelete]
        [Route("id/{recipeId:guid}")]
        public ActionResult DeleteById([FromHeader(Name = "X-User-Token")] Guid userToken, Guid recipeId)
        {            
            var session = SessionManager.GetSessionFor(userToken);
            if (session == null)
            {
                return StatusCode(403);
            }
            
            var ret = _dbWorkerService.DeleteRecipeById(recipeId, session.Username);
            if (!ret)
            {
                return BadRequest();    
            }

            return Ok();
        }
        

        [HttpGet]
        [Route("new")]
        public ActionResult GetByNew([FromHeader(Name = "X-User-Token")] Guid userToken)
        {
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
        [Route("user/{username}")]
        public ActionResult GetByUser([FromHeader(Name = "X-User-Token")] Guid userToken, string username)
        {
            var session = SessionManager.GetSessionFor(userToken);
            if (session == null)
            {
                return StatusCode(403);
            }

            var recipes = _dbWorkerService.FetchUserRecipes(username);
            if (recipes != null)
            {
                return Ok(recipes);
            }
            
            return BadRequest();
        }
        
    }
}
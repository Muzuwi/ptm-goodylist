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
    [Route("api/v1/recipe/")]
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
        [Route("fetchById")]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public ActionResult FetchById(FetchOneModel fetchOneModel)
        {
            var session = SessionManager.GetSessionFor(fetchOneModel.Token);
            if (session == null)
            {
                return StatusCode(403);
            }

            var recipe = _dbWorkerService.FetchRecipe(session.Username, fetchOneModel.RecipeId);
            if (recipe == null)
            {
                return NotFound();
            }

            return Ok(recipe);
        }
    }
}
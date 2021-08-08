using System;
using GoodyAPI.Models;
using GoodyAPI.Services;
using GoodyAPI.Services.Concrete;
using GoodyAPI.Services.Interfaces;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;

namespace GoodyAPI.Controllers
{
    [ApiController]
    [Route("api/v1/auth/")]
    public class AuthController : ControllerBase
    {
        private readonly ILogger<AuthController> _logger;
        private readonly IDbWorkerService _dbWorkerService;

        public AuthController(ILogger<AuthController> logger, IDbWorkerService worker)
        {
            _logger = logger;
            _dbWorkerService = worker;
        }
        
        [HttpPost]
        [Route("register")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public ActionResult Register(RegisterModel registerModel)
        {
            var result = _dbWorkerService.CreateUser(registerModel);
            if (!result)
            {
                return StatusCode(403);
            }
            
            return Ok();
        }
        
        
        [HttpPost]
        [Route("login")]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(UserToken))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public ActionResult Login(LoginModel loginModel)
        { 
            var userData = _dbWorkerService.FetchUserData(loginModel.Username);
            if (userData == null)
            {
                return StatusCode(403);
            }

            var hashedPassword = HashService.GetSha256HashString(loginModel.Password);
            if (hashedPassword != userData.PasswordHash)
            {
                return StatusCode(403);
            }

            var userSession = SessionManager.CreateSession(userData.Username);
            if (userSession == null)
            {
                return StatusCode(403);
            }

            return Ok(userSession.Token);
        }
        
        [HttpPost]
        [Route("logout")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status403Forbidden)]
        public ActionResult Logout(UserToken token)
        {
            var success = SessionManager.InvalidateSessionFor(token);
            if (!success)
            {
                return StatusCode(403);
            }
            
            return Ok();
        }
    }
}
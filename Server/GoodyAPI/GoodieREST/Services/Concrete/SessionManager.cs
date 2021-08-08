using System;
using System.Collections.Generic;
using GoodyAPI.Models;
using GoodyAPI.Services.Interfaces;
using Microsoft.Extensions.Logging;

namespace GoodyAPI.Services.Concrete
{
    public static class SessionManager
    {
        private static Dictionary<Guid, UserSession> Sessions = new Dictionary<Guid, UserSession>();

        public static bool IsSessionValid(UserToken userToken)
        {
            return Sessions.ContainsKey(userToken.Token);
        }

        public static UserSession GetSessionFor(UserToken token)
        {
            if (Sessions.TryGetValue(token.Token, out UserSession session))
            {
                return session;
            }

            return null;
        }

        public static UserSession CreateSession(string username)
        {
            UserToken token = new UserToken();
            token.Token = Guid.NewGuid();
            token.Created = DateTime.Now;
            token.Expires = DateTime.Now.AddDays(2);

            UserSession session = new UserSession();
            session.Token = token;
            session.Username = username;
            
            Sessions[token.Token] = session;

            return session;
        }

        public static bool InvalidateSessionFor(UserToken userToken)
        {
            if (!Sessions.ContainsKey(userToken.Token))
            {
                return false; 
            }
 
            return Sessions.Remove(userToken.Token);
        }
    }
}
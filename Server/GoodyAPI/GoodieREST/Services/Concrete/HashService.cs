using System.Security.Cryptography;
using System.Text;

namespace GoodyAPI.Services
{
    public static class HashService
    {

        public static byte[] GetSha256Hash(string inputString)
        {
            using (HashAlgorithm algorithm = SHA256.Create())
            {
                return algorithm.ComputeHash(Encoding.UTF8.GetBytes(inputString));
            }
        }
        
        public static string GetSha256HashString(string inputString)
        {
            StringBuilder builder = new StringBuilder();
            foreach (var bt in GetSha256Hash(inputString))
            {
                builder.Append(bt.ToString("x2"));
            }

            return builder.ToString();
        }
        
    }
}
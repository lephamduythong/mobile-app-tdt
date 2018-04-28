using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using WebApi.Models;

namespace WebApi.Controllers
{
    [Produces("application/json")]
    [Route("api/Test")]
    public class TestController : Controller
    {
        ShopOnlineContext _db;

        public TestController(ShopOnlineContext _context)
        {
            _db = _context;
        }

        [HttpGet]
        public IEnumerable<Product> Add1Product()
        {
            Product p = new Product()
            {
                Name = "CLGT",
                Price = 200,
                Image = 10, 
                Description = "Cute"
            };
            _db.Products.Add(p);
            _db.SaveChanges();
            return _db.Products;
        }


    }
}
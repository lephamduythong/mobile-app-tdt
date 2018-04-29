using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using WebApi.Models;

namespace WebApi.Controllers
{
    [Produces("application/json")]
    [Route("api/Bills")]
    public class BillsController : Controller
    {
        private readonly ShopOnlineContext _context;

        public BillsController(ShopOnlineContext context)
        {
            _context = context;
        }

        // GET: api/Bills
        [HttpGet]
        public IEnumerable<Bill> GetBills()
        {
            return _context.Bills;
        }

        // GET: api/Bills/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetBill([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var Bill = await _context.Bills.SingleOrDefaultAsync(m => m.Id == id);

            if (Bill == null)
            {
                return NotFound();
            }

            return Ok(Bill);
        }

        [HttpGet("GetByCustomer/{idCust}")]
        public async Task<IActionResult> GetByCustomer([FromRoute] int idCust)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var bills = await _context.Bills.Where(m => m.CustomerId == idCust).ToListAsync();

            if (bills == null)
            {
                return NotFound(); // 404
            }

            return Ok(bills);
        }

        [HttpGet("GetNewBills")]
        public async Task<IActionResult> GetNewBills()
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var Bills = await _context.Bills.Where(m => m.Status == 1).ToListAsync();

            if (Bills == null)
            {
                return NotFound();
            }

            return Ok(Bills);
        }

        // POST: api/Bills
        [HttpPost]
        public async Task<IActionResult> PostBill([FromBody] Bill Bill)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.Bills.Add(Bill);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetBill", new { id = Bill.Id }, Bill);
        }

        private bool BillExists(int id)
        {
            return _context.Bills.Any(e => e.Id == id);
        }
    }
}
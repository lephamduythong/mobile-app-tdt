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
    [Route("api/BillDetails")]
    public class BillDetailsController : Controller
    {
        private readonly ShopOnlineContext _context;

        public BillDetailsController(ShopOnlineContext context)
        {
            _context = context;
        }

        [HttpGet]
        public IEnumerable<BillDetail> GetBillDetails()
        {
            return _context.BillDetails;
        }

        // GET: api/BillDetails/5
        [HttpGet("{idBill}")]
        public async Task<IActionResult> GetBillDetail([FromRoute] int idBill)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var billDetail = await _context.BillDetails.Where(m => m.BillId == idBill).ToListAsync();

            if (billDetail == null)
            {
                return NotFound();
            }

            return Ok(billDetail);
        }

        [HttpPost]
        public async Task<IActionResult> PostBillDetail([FromBody] BillDetail billDetail)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.BillDetails.Add(billDetail);
            await _context.SaveChangesAsync();

            return Ok(billDetail);
            // return CreatedAtAction("GetBillDetails", new { id = Bill.Id }, Bill);
        }

        // POST: api/BillDetails
        // [HttpPost]
        // public async Task<IActionResult> Postbill([FromBody] BillDetail bill)
        // {
        //     if (!ModelState.IsValid)
        //     {
        //         return BadRequest(ModelState);
        //     }

        //     _context.BillDetails.Add(bill);
        //     await _context.SaveChangesAsync();

        //     // return CreatedAtAction("Getbill", new { id = bill.Id }, bill);
        // }
    }
}
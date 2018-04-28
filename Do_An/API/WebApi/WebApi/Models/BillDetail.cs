using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace WebApi.Models
{
    public class BillDetail
    {
        public int ProductId { get; set; }
        public int BillId { get; set; }
        public int Quantity { get; set; }

        // public virtual Product Product { get; set; }
        // public virtual Bill Bill { get; set; }
    }
}

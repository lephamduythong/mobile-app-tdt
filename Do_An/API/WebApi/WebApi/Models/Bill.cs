using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace WebApi.Models
{
    public class Bill
    {
        [Key]
        public int Id { get; set; }
        public string DateCreated { get; set; }
        public int Status { get; set; }
        public int CustomerId { get; set; }
        public string ProductData { get; set; }
        // public virtual Customer Customer { get; set; }
    }
}

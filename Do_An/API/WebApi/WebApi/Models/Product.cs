using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace WebApi.Models
{
    public class Product
    {
        [Key]
        public int Id { get; set; }
        public String Name { get; set; }
        public int Price { get; set; }
        public int Image { get; set; }
        public String Description { get; set; }
        public int Status { get; set; }
        public int CategoryId { get; set; }
    }
}

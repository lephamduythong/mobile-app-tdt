using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace MobileApi.Models
{
    public class Student
    {
        [Key]
        public long Id { get; set; }
        public string Name { get; set; }
        public string Gender { get; set; }
        public bool IsComplete { get; set; }
    }
}

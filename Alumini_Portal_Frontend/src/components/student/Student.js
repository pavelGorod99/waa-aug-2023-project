import { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from "react-router-dom";
import Swal from 'sweetalert2'
import StudentList from './StudentList';


const Student = () => {
  const entity = "student";

  const [students, setStudents] = useState([]);
  const navigate = useNavigate();

  const getStudents = async () => {
    try {
      const result = await axios.get("/students", {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("authToken")}`
        }
      });
      setStudents(result.data);
    } catch (error) {
      console.error(error);
    }
  }

  const addNewStudent = (newStudent) => {
    setStudents([...students, newStudent]);
  };

  useEffect(() => {
    getStudents();
  }, [])

  const handleUpdate = async (id) => {
    navigate("/update-students/" + id);
  }

  const handleDelete = (id) => {
    Swal.fire({
      title: `Are you sure you want to delete this ${entity}?`,
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then(async (result) => {
      if (result.isConfirmed) {
        try {
          await axios.delete(`/students/${id}`, {
            headers: {
              Authorization: `Bearer ${localStorage.getItem("authToken")}`
            }
          });
          Swal.fire(
            'Success!',
            `The ${entity} has been deleted successfully.`,
            'success'
          )
          getStudents();
        } catch (error) {
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: `An error occurred while deleting the ${entity}, please try again`,
          })
          console.error(error);
        }

      }
    })
  }

  return (
    <>
      {students && <StudentList students={students} title="All Students" handleUpdate={handleUpdate} handleDelete={handleDelete} />}
    </>
  );
}

export default Student;
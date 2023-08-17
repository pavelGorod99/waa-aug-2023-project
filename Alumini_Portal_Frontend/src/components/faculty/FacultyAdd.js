import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Swal from 'sweetalert2'

const FacultyAdd = () => {
  const entity = "faculty";

  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const [title, setTitle] = useState('');
  const [isPending, setIsPending] = useState(false);
  const navigate = useNavigate();

  const handleFacultyCreate = async (e) => {
    e.preventDefault();
    setIsPending(true);

    try {
      const faculty = { firstName, lastName, email, phone, title };
      await addFaculty(faculty);
      Swal.fire({
        title: `${entity} added successfully`,
        text: `Would you like to add another ${entity}?`,
        icon: 'success',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, I want to add another'
      }).then((result) => {
        if (!result.isConfirmed) {
          navigate('/show-faculties');
        }
        resetForm();
      })
    } catch (error) {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: `An error occurred while adding the ${entity}, please try again`,
      })
      console.error(error);
    } finally {
      setIsPending(false);
    }
  }

  const addFaculty = async (faculty) => {
    await axios.post("/faculties", faculty, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("authToken")}`
      }
    });
  }

  const resetForm = () => {
    setFirstName('');
    setLastName('');
    setEmail('');
    setPhone('');
    setTitle('');
  }

  return (
    <>
      <div id="createCourseDiv" className="container">
        <h2>Create {entity}</h2>

        <div className="mt-5">

          <form id="createCourseForm" onSubmit={handleFacultyCreate}>

            <div className="mb-3">
              <label htmlFor="firstNameInput" className="form-label">First Name</label>
              <input type="text" className="form-control" id="firstNameInput" value={firstName} onChange={e => setFirstName(e.target.value)} required />
            </div>

            <div className="mb-3">
              <label htmlFor="lastNameInput" className="form-label">Last Name</label>
              <input type="text" className="form-control" id="lastNameInput" value={lastName} onChange={e => setLastName(e.target.value)} required />
            </div>

            <div className="mb-3">
              <label htmlFor="emailInput" className="form-label">Email</label>
              <input type="text" className="form-control" id="emailInput" value={email} onChange={e => setEmail(e.target.value)} required />
            </div>

            <div className="mb-3">
              <label htmlFor="phoneInput" className="form-label">Phone</label>
              <input type="text" className="form-control" id="phoneInput" value={phone} onChange={e => setPhone(e.target.value)} required />
            </div>

            <div className="mb-3">
              <label htmlFor="titleInput" className="form-label">Title</label>
              <input type="text" className="form-control" id="titleInput" value={title} onChange={e => setTitle(e.target.value)} required />
            </div>

            {isPending ? <button type="submit" className="btn btn-secondary" disabled>Adding {entity}...</button> :
              <button type="submit" className="btn btn-primary">Add {entity}</button>}

          </form>
        </div>
      </div>
    </>
  );
}

export default FacultyAdd;
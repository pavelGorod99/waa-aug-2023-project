import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Swal from 'sweetalert2'

const JobAdvertAdd = () => {
  const entity = "Job Advert";

  const [jobTitle, setJobTitle] = useState('');
  const [expectedSalary, setExpectedSalary] = useState('');
  const [jobDescription, setJobDescription] = useState('');
  const [companyName, setCompanyName] = useState('');
  const [isPending, setIsPending] = useState(false);
  const navigate = useNavigate();

  const handleJobAdvertCreate = async (e) => {
    e.preventDefault();
    setIsPending(true);

    try {
      const jobAdvert = { jobTitle, expectedSalary, jobDescription, companyName };
      await addJobAdvert(jobAdvert);
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
          navigate('/show-job-adverts');
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

  const addJobAdvert = async (jobAdvert) => {
    await axios.post("/job-adverts", jobAdvert, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("authToken")}`
      }
    });
  }

  const resetForm = () => {
    setJobTitle('');
    setExpectedSalary('');
    setJobDescription('');
    setCompanyName('');
  }

  return (
    <>
      <div id="createCourseDiv" className="container">
        <h2>Create {entity}</h2>

        <div className="mt-5">

          <form id="createCourseForm" onSubmit={handleJobAdvertCreate}>

            <div className="mb-3">
              <label htmlFor="jobTitleInput" className="form-label">Job Title</label>
              <input type="text" className="form-control" id="jobTitleInput" value={jobTitle} onChange={e => setJobTitle(e.target.value)} required />
            </div>

            <div className="mb-3">
              <label htmlFor="expectedSalaryInput" className="form-label">Expected Salary</label>
              <input type="number" className="form-control" id="expectedSalaryInput" value={expectedSalary} onChange={e => setExpectedSalary(e.target.value)} required />
            </div>

            <div className="mb-3">
              <label htmlFor="emailInput" className="form-label">Job Description</label>
              <input type="text" className="form-control" id="emailInput" value={jobDescription} onChange={e => setJobDescription(e.target.value)} required />
            </div>

            <div className="mb-3">
              <label htmlFor="phoneInput" className="form-label">Company Name</label>
              <input type="text" className="form-control" id="phoneInput" value={companyName} onChange={e => setCompanyName(e.target.value)} required />
            </div>

            {isPending ? <button type="submit" className="btn btn-secondary" disabled>Adding {entity}...</button> :
              <button type="submit" className="btn btn-primary">Add {entity}</button>}

          </form>
        </div>
      </div>
    </>
  );
}

export default JobAdvertAdd;
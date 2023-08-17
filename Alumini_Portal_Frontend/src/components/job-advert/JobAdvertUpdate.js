import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useParams } from 'react-router-dom';
import Swal from 'sweetalert2'


const JobAdvertUpdate = () => {
    const entity = "Job Advert";

    const { id } = useParams();
    const [jobAdvert, setJobAdvert] = useState(null);

    const [jobTitle, setJobTitle] = useState('');
    const [expectedSalary, setExpectedSalary] = useState('');
    const [jobDescription, setJobDescription] = useState('');
    const [companyName, setCompanyName] = useState('');

    const [isPending, setIsPending] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        const getFaculty = async () => {
            try {
                const result = await axios.get(`/job-adverts/${id}`, {
                    headers: {
                        Authorization: `Bearer ${localStorage.getItem("authToken")}`
                    }
                });
                setJobAdvert(result.data);
                setJobTitle(result.data.jobTitle);
                setExpectedSalary(result.data.expectedSalary);
                setJobDescription(result.data.jobDescription);
                setCompanyName(result.data.companyName);
            } catch (error) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: `An error occurred while fetching the ${entity} with id ${id}, please try again`,
                })
                console.error(error);
                navigate("/show-job-adverts");
            }

        }
        getFaculty();
    }, [id, navigate])

    const handleFacultyUpdate = async (e) => {
        e.preventDefault();
        setIsPending(true);

        try {
            const updatedFaculty = { jobTitle, expectedSalary, jobDescription, companyName };

            await updateFaculty(updatedFaculty);
            Swal.fire(
                'Success!',
                `The ${entity} has been updated successfully.`,
                'success'
            )
            navigate("/show-job-adverts");
        } catch (error) {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: `An error occurred while updating the ${entity}, please try again`,
            })
            console.error(error);
        } finally {
            setIsPending(false);
        }
    }

    const updateFaculty = async (updatedFaculty) => {
        await axios.put(`/job-adverts/${id}`, updatedFaculty);
    }

    return (
        <>
            {jobAdvert && <div className="container">
                <h2>Update {entity}</h2>

                <div className="mt-5">

                    <form onSubmit={handleFacultyUpdate}>
                        <input type="hidden" id="updateFacultyId" value={jobAdvert.id} />

                        <div className="mb-3">
                            <label htmlFor="jobTitleInput" className="form-label">First Name</label>
                            <input type="text" className="form-control" id="jobTitleInput" value={jobTitle}
                                onChange={e => setJobTitle(e.target.value)} />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="expectedSalaryInput" className="form-label">Last Name</label>
                            <input type="text" className="form-control" id="expectedSalaryInput" value={expectedSalary}
                                onChange={e => setExpectedSalary(e.target.value)} />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="jobDescriptionInput" className="form-label">Job Description</label>
                            <input type="text" className="form-control" id="jobDescriptionInput" value={jobDescription}
                                onChange={e => setJobDescription(e.target.value)} />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="companyNameInput" className="form-label">Company Name</label>
                            <input type="text" className="form-control" id="companyNameInput" value={companyName}
                                onChange={e => setCompanyName(e.target.value)} />
                        </div>

                        {isPending ?
                            <button type="submit" className="btn btn-disabled">Updating {entity}...</button> :
                            <button type="submit" className="btn btn-primary">Update {entity}</button>}

                    </form>
                </div>
            </div>}
        </>
    );
}

export default JobAdvertUpdate;
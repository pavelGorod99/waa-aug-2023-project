import { useEffect, useState } from 'react';
import axios from 'axios';
import Swal from 'sweetalert2'


const ApplyToJobs = () => {
    const entity = "Job Advert";

    const [jobAdverts, setJobAdverts] = useState([]);

    const getJobAdverts = async () => {
        try {
            const result = await axios.get("/job-adverts", {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("authToken")}`
                }
            });
            setJobAdverts(result.data);
        } catch (error) {
            console.error(error);
        }
    }

    useEffect(() => {
        getJobAdverts();
    }, [])

    const handleJobApply = (id) => {
        Swal.fire({
            title: `Please confirm that you want to apply to this ${entity}?`,
            text: "Your details including your CV will be submitted to the job poster",
            icon: 'question',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, I would like to apply!'
        }).then(async (result) => {
            if (result.isConfirmed) {
                try {
                    const studentId = 2;
                    await axios.put(`/jobs-applied/${id}`, studentId, {
                        headers: {
                            Authorization: `Bearer ${localStorage.getItem("authToken")}`
                        }
                    });
                    Swal.fire(
                        'Success!',
                        `You have successfully applied to this ${entity}.`,
                        'success'
                    )
                    getJobAdverts();
                } catch (error) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: `An error occurred while applying to the ${entity}, please try again`,
                    })
                    console.error(error);
                }

            }
        })
    }

    return (
        <>
            {jobAdverts &&
                <div>
                    <h2>Apply to {entity}s</h2>
                    <div className="mt-5">
                        <table className="table">
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Job Title</th>
                                    <th scope="col">Job Description</th>
                                    <th scope="col">Company Name</th>
                                    <th scope="col">Expected Salary</th>
                                    <th scope="col">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    jobAdverts.map((jobAdvert, index) => (
                                        <tr key={jobAdvert.id}>
                                            <td>{index + 1}</td>
                                            <td>{jobAdvert.jobTitle}</td>
                                            <td>{jobAdvert.jobDescription}</td>
                                            <td>{jobAdvert.companyName}</td>
                                            <td>{jobAdvert.expectedSalary}</td>
                                            <td><button className="btn btn-sm btn-info" onClick={() => handleJobApply(jobAdvert.id)}>Apply to this Job</button></td>
                                        </tr>
                                    ))
                                }
                            </tbody>
                        </table>
                    </div>
                </div>
            }
        </>
    );
}

export default ApplyToJobs;
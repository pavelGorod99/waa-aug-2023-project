import { Link } from "react-router-dom";

const JobAdvertList = ({ jobAdverts, title, handleUpdate, handleDelete }) => {

    return (
        <>
            <div>
                <div className="d-flex justify-content-between">
                    <div>
                        <h2>{title}</h2>
                    </div>
                    <div>
                        <Link to="/create-job-adverts" className="btn btn-md btn-success">Add new Job Advert</Link>
                    </div>
                </div>
                <div className="mt-5">
                    <table className="table">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Job Title</th>
                                <th scope="col">Job Description</th>
                                <th scope="col">Company Name</th>
                                <th scope="col">Expected Salary</th>
                                <th scope="col">Update</th>
                                <th scope="col">Delete</th>
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
                                        <td><button className="btn btn-sm btn-primary" onClick={() => handleUpdate(jobAdvert.id)}>Update</button></td>
                                        <td><button className="btn btn-sm btn-danger" onClick={() => handleDelete(jobAdvert.id)}>Delete</button></td>
                                    </tr>
                                ))
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        </>
    );
}

export default JobAdvertList;
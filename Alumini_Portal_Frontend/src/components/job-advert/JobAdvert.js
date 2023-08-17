import { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from "react-router-dom";
import Swal from 'sweetalert2'
import JobAdvertList from './JobAdvertList';


const JobAdvert = () => {
    const entity = "job advert";

    const [jobAdverts, setJobAdverts] = useState([]);
    const navigate = useNavigate();

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

    const handleUpdate = async (id) => {
        navigate("/update-job-adverts/" + id);
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
                    await axios.delete(`/job-adverts/${id}`, {
                        headers: {
                            Authorization: `Bearer ${localStorage.getItem("authToken")}`
                        }
                    });
                    Swal.fire(
                        'Success!',
                        `The ${entity} has been deleted successfully.`,
                        'success'
                    )
                    getJobAdverts();
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
            {jobAdverts && <JobAdvertList jobAdverts={jobAdverts} title="All Job Adverts" handleUpdate={handleUpdate} handleDelete={handleDelete} />}
        </>
    );
}

export default JobAdvert;
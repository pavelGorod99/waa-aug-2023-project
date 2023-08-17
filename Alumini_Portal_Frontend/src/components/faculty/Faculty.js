import { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from "react-router-dom";
import Swal from 'sweetalert2'
import FacultyList from './FacultyList';


const Faculty = () => {
    const entity = "Faculty";

    const [faculties, setFaculties] = useState([]);

    const navigate = useNavigate();

    const getFaculties = async () => {

        try {

            const result = await axios.get("/faculties", {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("authToken")}`
                }
            });

            setFaculties(result.data);

        } catch (error) {
            console.error(error);
        }
    }

    useEffect(() => {
        getFaculties();
    }, [])

    const handleUpdate = async (id) => {
        navigate("/update-faculties/" + id);
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
                    await axios.delete(`/faculties/${id}`, {
                        headers: {
                            Authorization: `Bearer ${localStorage.getItem("authToken")}`
                        }
                    });
                    Swal.fire(
                        'Success!',
                        `The ${entity} has been deleted successfully.`,
                        'success'
                    )
                    getFaculties();
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
            {faculties && <FacultyList faculties={faculties} title="All faculties" handleUpdate={handleUpdate} handleDelete={handleDelete} />}
        </>
    );
}

export default Faculty;
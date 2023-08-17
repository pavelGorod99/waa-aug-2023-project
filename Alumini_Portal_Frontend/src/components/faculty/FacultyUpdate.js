import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useParams } from 'react-router-dom';
import Swal from 'sweetalert2'


const FacultyUpdate = () => {
    const entity = "faculty";

    const { id } = useParams();
    const [faculty, setFaculty] = useState(null);

    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [phone, setPhone] = useState('');
    const [title, setTitle] = useState('');
    const [isPending, setIsPending] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        const getFaculty = async () => {
            try {
                const result = await axios.get(`/faculties/${id}`, {
                    headers: {
                        Authorization: `Bearer ${localStorage.getItem("authToken")}`
                    }
                });
                setFaculty(result.data);
                setFirstName(result.data.firstName);
                setLastName(result.data.lastName);
                setEmail(result.data.email);
                setPhone(result.data.phone);
                setTitle(result.data.title);
            } catch (error) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: `An error occurred while fetching the ${entity} with id ${id}, please try again`,
                })
                console.error(error);
                navigate("/show-faculties");
            }

        }
        getFaculty();
    }, [id, navigate])

    const handleFacultyUpdate = async (e) => {
        e.preventDefault();
        setIsPending(true);

        try {
            const updatedFaculty = { firstName, lastName, email, phone, title };

            await updateFaculty(updatedFaculty);
            Swal.fire(
                'Success!',
                `The ${entity} has been updated successfully.`,
                'success'
            )
            navigate("/show-faculties");
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
        await axios.put(`/faculties/${id}`, updatedFaculty, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("authToken")}`
            }
        });
    }

    return (
        <>
            {faculty && <div className="container">
                <h2>Update {entity}</h2>

                <div className="mt-5">

                    <form onSubmit={handleFacultyUpdate}>
                        <input type="hidden" id="updateFacultyId" value={faculty.id} />

                        <div className="mb-3">
                            <label htmlFor="firstNameInput" className="form-label">First Name</label>
                            <input type="text" className="form-control" id="firstNameInput" value={firstName}
                                onChange={e => setFirstName(e.target.value)} />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="lastNameInput" className="form-label">Last Name</label>
                            <input type="text" className="form-control" id="lastNameInput" value={lastName}
                                onChange={e => setLastName(e.target.value)} />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="emailInput" className="form-label">Email</label>
                            <input type="text" className="form-control" id="emailInput" value={email}
                                onChange={e => setEmail(e.target.value)} />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="phoneInput" className="form-label">Phone</label>
                            <input type="text" className="form-control" id="phoneInput" value={phone}
                                onChange={e => setPhone(e.target.value)} />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="titleInput" className="form-label">Title</label>
                            <input type="text" className="form-control" id="titleInput" value={title}
                                onChange={e => setTitle(e.target.value)} />
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

export default FacultyUpdate;
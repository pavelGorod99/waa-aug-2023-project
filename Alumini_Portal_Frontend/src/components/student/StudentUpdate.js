import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useParams } from 'react-router-dom';
import Swal from 'sweetalert2'


const StudentUpdate = () => {
    const entity = "student";

    const { id } = useParams();
    const [student, setStudent] = useState(null);
    const [initialCV, setInitialCV] = useState('');

    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [phone, setPhone] = useState('');
    const [cv, setCv] = useState('');
    const [isCurrentlyEmployed, setIsCurrentlyEmployed] = useState('');
    const [isPending, setIsPending] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        const getStudent = async () => {
            try {
                const result = await axios.get(`/students/${id}`, {
                    headers: {
                        Authorization: `Bearer ${localStorage.getItem("authToken")}`
                    }
                });
                setStudent(result.data);
                setFirstName(result.data.firstName);
                setLastName(result.data.lastName);
                setEmail(result.data.email);
                setPhone(result.data.phone);
                setCv(result.data.cv);
                setInitialCV(result.data.cv);
                setIsCurrentlyEmployed(result.data.isCurrentlyEmployed);
            } catch (error) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: `An error occurred while fetching the ${entity} with id ${id}, please try again`,
                })
                console.error(error);
                navigate("/");
            }

        }
        getStudent();
    }, [id, navigate])

    const handleStudentUpdate = async (e) => {
        e.preventDefault();
        setIsPending(true);

        try {
            let updatedStudent = {}
            if (cv === initialCV) {
                // cv was not changed
                updatedStudent = { firstName, lastName, email, phone, cv, isCurrentlyEmployed };
            } else {
                // cv was changed/file was selected
                const base64CV = await convertFileToBase64(cv);
                updatedStudent = { firstName, lastName, email, phone, cv: base64CV, isCurrentlyEmployed };
            }
            await updateStudent(updatedStudent);
            Swal.fire(
                'Success!',
                `The ${entity} has been updated successfully.`,
                'success'
            )
            navigate("/");
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

    const convertFileToBase64 = (file) => {
        return new Promise((resolve, reject) => {
            const reader = new FileReader();
            reader.onload = () => {
                const base64String = reader.result.split(',')[1];
                resolve(base64String);
            }
            reader.onerror = (error) => {
                reject(error);
            };
            reader.readAsDataURL(file);
        })
    }

    const updateStudent = async (updatedStudent) => {
        await axios.put(`/students/${id}`, updatedStudent, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("authToken")}`
            }
        });
    }

    return (
        <>
            {student && <div className="container">
                <h2>Update {entity}</h2>

                <div className="mt-5">

                    <form onSubmit={handleStudentUpdate}>
                        <input type="hidden" id="updateStudentId" value={student.id} />

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
                            <label htmlFor="employmentStatusInput" className="form-label">employment status</label>
                            <select className="custom-select d-block w-100" id="employmentStatusInput" value={isCurrentlyEmployed}
                                onChange={e => setIsCurrentlyEmployed(e.target.value)} required>
                                <option value="">Select</option>
                                <option value="Yes">Yes, I am currently employed</option>
                                <option value="No">No, I am not currently employed</option>
                            </select>
                        </div>

                        <div className="mb-3">
                            <label htmlFor="cvInput" className="form-label">Update CV: {initialCV}</label>
                            <input type="file" className="form-control" id="cvInput" onChange={e => setCv(e.target.files[0])} />
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

export default StudentUpdate;
import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

const StudentList = ({ students, title, handleUpdate, handleDelete }) => {

    const [downloadLinks, setDownloadLinks] = useState({});

    useEffect(() => {
        const fetchDownloadLinks = async () => {
            const links = {};
            for (const student of students) {
                try {
                    if (student.cv == null) {
                        links[student.id] = null;
                    } else {
                        let cvString = extractFileName(student.cv);
                        links[student.id] = cvString;
                    }
                } catch (error) {
                    console.error(error);
                    links[student.id] = null;
                }
            }
            setDownloadLinks(links);
        };

        fetchDownloadLinks();
    }, [students]);

    const extractFileName = (filePath) => {
        const fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        return fileName;
    };

    const handleDownload = async (fileName) => {
        let res = await axios.get(`/downloads/${fileName}`, { responseType: "blob" }, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("authToken")}`
            }
        });
        const url = window.URL.createObjectURL(new Blob([res.data]));
        const link = document.createElement("a");
        link.href = url;
        link.setAttribute("download", fileName);
        document.body.appendChild(link);
        link.click();
    };

    return (
        <>
            <div>
                <div className="d-flex justify-content-between">
                    <div>
                        <h2>{title}</h2>
                    </div>
                    <div>
                        <Link to="/create-students" className="btn btn-md btn-success">Add new Student</Link>
                    </div>
                </div>


                <div className="mt-5">
                    <table className="table">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">First Name</th>
                                <th scope="col">Last Name</th>
                                <th scope="col">Email</th>
                                <th scope="col">Phone</th>
                                <th scope="col">CV</th>
                                <th scope="col">Employed?</th>
                                <th scope="col">Update</th>
                                <th scope="col">Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                students.map((student, index) => (
                                    <tr key={student.id}>
                                        <td>{index + 1}</td>
                                        <td>{student.firstName}</td>
                                        <td>{student.lastName}</td>
                                        <td>{student.email}</td>
                                        <td>{student.phone}</td>
                                        <td>
                                            {downloadLinks[student.id] ? (
                                                <button className="btn btn-sm btn-info" onClick={() => handleDownload(downloadLinks[student.id])}
                                                    title={`Download ${student.firstName}'s cv`}
                                                >
                                                    Download
                                                </button>) : ("N/A")}
                                        </td>
                                        <td>{student.isCurrentlyEmployed}</td>
                                        <td><button className="btn btn-sm btn-primary" onClick={() => handleUpdate(student.id)}>Update</button></td>
                                        <td><button className="btn btn-sm btn-danger" onClick={() => handleDelete(student.id)}>Delete</button></td>
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

export default StudentList;
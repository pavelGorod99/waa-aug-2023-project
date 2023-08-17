import SignInImage from '../../assets/login-otp-banner.webp';
import { useState } from "react";
import axios from "axios";
import { useNavigate } from 'react-router-dom';


const Register = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [role, setRole] = useState("");

  const navigate = useNavigate();

  const handleLoginSuccess = (token) => {
    localStorage.setItem("authToken", token);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("/users/register", { firstName, lastName, email, password, role });
      handleLoginSuccess(response.data.token);
    } catch (error) {
      console.error("Registration failed! \n", error);
    }
  };




  return (
    <div className="form-signin-body">

      <div className="row">

        <div className="col-lg-6 siginIn-Image-parent">
          <img className="mb-4" id='signIn-Image' src={SignInImage} alt="sign-in" />
        </div>

        <div className="col-lg-6">
          <form className="form-signin" onSubmit={handleSubmit}>

            <h1 className="h3 mb-3 font-weight-normal">Register</h1>

            <div className="mb-1">
              <label htmlFor="inputEmail" className="sr-only">First Name</label>
              <input type="text"
                id="inputEmail"
                className="form-control"
                required autoFocus
                value={firstName} onChange={e => setFirstName(e.target.value)} />
            </div>

            <div className="mb-1">
              <label htmlFor="inputEmail" className="sr-only">Last Name</label>
              <input type="text"
                id="inputEmail"
                className="form-control"
                required autoFocus
                value={lastName} onChange={e => setLastName(e.target.value)} />
            </div>

            <div className="mb-1">
              <label htmlFor="inputEmail" className="sr-only">Email address</label>
              <input type="email"
                id="inputEmail"
                className="form-control"
                required autoFocus
                value={email} onChange={e => setEmail(e.target.value)} />
            </div>



            <div className="mb-1">
              <label htmlFor="inputPassword" className="sr-only">Password</label>
              <input type="password"
                id="inputPassword"
                className="form-control"
                required
                value={password} onChange={e => setPassword(e.target.value)}
              />
            </div>

            <div className="mb-1">
              <label htmlFor="employmentStatusInput" className="form-label">Role</label>
              <select className="custom-select d-block w-100" id="employmentStatusInput" onChange={e => setRole(e.target.value)}>
                <option value="select one">Select Role</option>
                <option value="STUDENT">Student</option>
                <option value="FACULTY">Faculty</option>
              </select>
            </div>

            <div className="checkbox mb-3">
              <label>
                <input type="checkbox" value="remember-me" /> Remember me
              </label>
            </div>

            <button className="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>

            <p className="mt-5 mb-3 text-muted">&copy; Group : Pavel Ivan Berhane</p>
          </form>
        </div>
      </div>

    </div>
  );
}

export default Register;
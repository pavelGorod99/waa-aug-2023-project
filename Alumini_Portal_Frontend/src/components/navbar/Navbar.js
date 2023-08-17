import { Link } from "react-router-dom";

const Navbar = () => {

  return (
    <>
      <div className="container">
        <header className="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
          <Link to="/"
            className="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">
            <span className="fs-4">Alumni Management Portal</span>
          </Link>

          <ul className="nav nav-pills">
            <li className="nav-item nav-link">
              <Link to="/" className="nav-link">
                Show Students
              </Link>
            </li>
            <li className="nav-item nav-link">
              <Link to="/apply-to-jobs" className="nav-link">
                Apply to Jobs
              </Link>
            </li>
            <li className="nav-item nav-link">
              <Link to="/show-faculties" className="nav-link">
                Show Faculties
              </Link>
            </li>
            <li className="nav-item nav-link">
              <Link to="/show-job-adverts" className="nav-link">
                Show Job Adverts
              </Link>
            </li>
            <li className="nav-item nav-link">
              <Link to="/dashboard" className="nav-link">
                Dashboard
              </Link>
            </li>
            <li className="nav-item nav-link">
              <Link to="/sign-in" className="nav-link">
                Sign In
              </Link>
            </li>
            <li className="nav-item nav-link">
              <Link to="/sign-up" className="nav-link">
                Sign Up
              </Link>
            </li>
          </ul>

        </header>
      </div>
    </>
  );
};

export default Navbar;

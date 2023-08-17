import { BrowserRouter as Router, useRoutes } from "react-router-dom";
import { PrimeReactProvider } from 'primereact/api';

import './App.css';

import Navbar from './components/navbar/Navbar';
import SignIn from './components/sign-in/SignIn';
import JobApplied from './components/job-applied/JobApplied';
import StudentList from './components/student/StudentList';
import StudentAdd from './components/student/StudentAdd';
import StudentUpdate from './components/student/StudentUpdate';
import NotFound from './components/not-found/NotFound';
import Home from './components/home/Home';
import Faculty from './components/faculty/Faculty';
import FacultyAdd from './components/faculty/FacultyAdd';
import FacultyUpdate from './components/faculty/FacultyUpdate';
import Dashboard from './components/home/Dashboard';
import JobAdvert from "./components/job-advert/JobAdvert";
import JobAdvertAdd from "./components/job-advert/JobAdvertAdd";
import JobAdvertUpdate from "./components/job-advert/JobAdvertUpdate";
import Register from "./components/register/Register";

const MyRoutes = () => {
  const routes = useRoutes([
    { path: "/", element: <Home /> },
    { path: "/sign-in", element: <SignIn /> },
    { path: "/sign-up", element: <Register /> },
    { path: "/apply-to-jobs", element: <JobApplied /> },
    { path: "/show-students", element: <StudentList /> },
    { path: "/create-students", element: <StudentAdd /> },
    { path: "/update-students/:id", element: <StudentUpdate /> },
    { path: "/show-faculties", element: <Faculty /> },
    { path: "/create-faculties", element: <FacultyAdd /> },
    { path: "/update-faculties/:id", element: <FacultyUpdate /> },
    { path: "/show-job-adverts", element: <JobAdvert /> },
    { path: "/create-job-adverts", element: <JobAdvertAdd /> },
    { path: "/update-job-adverts/:id", element: <JobAdvertUpdate /> },
    { path: "/dashboard", element: <Dashboard /> },
    { path: "*", element: <NotFound /> },
  ]);
  return routes;
};


function App() {

  return (
    <Router>
      <PrimeReactProvider>
        <div className="App">
          <div className="container">
            <Navbar />
            <div className="content">
              <MyRoutes />
            </div>
          </div>
        </div>
      </PrimeReactProvider>
    </Router>
  );
}

export default App;

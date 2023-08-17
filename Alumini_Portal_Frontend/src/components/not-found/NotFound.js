import { Link } from "react-router-dom";

const NotFound = () => {
    return (
        <>
            <div className="container not-found">
                <h2>404 Error</h2>
                <p>Page not found</p>
                <p><Link to='/'>Click here to go back to the homepage</Link> </p>
            </div>
        </>
    );
}

export default NotFound;
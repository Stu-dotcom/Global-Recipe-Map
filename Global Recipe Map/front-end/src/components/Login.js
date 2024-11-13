import React, { useState } from 'react';
import './Login.css';

const Login = ({ onLoginSuccess }) => {
    const [isLoginMode, setIsLoginMode] = useState(true); // Toggle between login and sign up
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(null);
    const apiUrl = process.env.REACT_APP_API_BASE_URL;

    // Toggle between login and sign-up mode
    const toggleMode = () => {
        setIsLoginMode(!isLoginMode);
        setError(null);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const url = isLoginMode
            ? `${apiUrl}/api/login`
            : `${apiUrl}/api/signup`;

        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                mode: 'cors',
                body: JSON.stringify({ username, password }),
            });

            if (response.ok) {
                const data = await response.json();

                if (isLoginMode) {
                    // Store token or session info in localStorage
                    localStorage.setItem('authToken', data.token);
                    onLoginSuccess(data); // Notify parent component of successful login
                } else {
                    alert('Account created successfully! Please log in.');
                    setIsLoginMode(true); // Switch to login mode
                }
            } else {
                const errorMsg = isLoginMode
                    ? 'Invalid username or password'
                    : 'Failed to create account. Username may already be taken.';
                setError(errorMsg);
            }
        } catch (error) {
            console.error(isLoginMode ? 'Login error:' : 'Signup error:', error);
            setError('An error occurred. Please try again.');
        }
    };

    return (
        <div className="login-container">
            <h2>{isLoginMode ? 'Login' : 'Sign Up'}</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Username:
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                </label>
                <label>
                    Password:
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </label>
                {error && <p className="error">{error}</p>}
                <button type="submit">{isLoginMode ? 'Login' : 'Sign Up'}</button>
            </form>
            <button onClick={toggleMode} className="toggle-mode-button">
                {isLoginMode ? 'Create an Account' : 'Already have an account? Login'}
            </button>
        </div>
    );
};

export default Login;

import React from 'react';
import './App.css';
import MapComponent from './components/MapComponent';
import RecipeForm from './components/RecipeForm';

function App() {
    const handleRecipeSubmit = async (recipe) => {
        try {
            const response = await fetch('http://localhost:8080/api/recipes', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(recipe),
            });

            if (response.ok) {
                const data = await response.json();
                console.log('Recipe submitted successfully:', data);
                alert('Recipe submitted successfully!');
            } else {
                console.error('Failed to submit recipe:', response.statusText);
                alert('Failed to submit recipe. Please try again.');
            }
        } catch (error) {
            console.error('Error submitting recipe:', error);
            alert('An error occurred while submitting the recipe.');
        }
    };

    return (
        <div className="App">
            <h1>Leaflet Map with OpenStreetMap Tiles</h1>
            <MapComponent />
            <RecipeForm onSubmit={handleRecipeSubmit} />
        </div>
    );
}

export default App;
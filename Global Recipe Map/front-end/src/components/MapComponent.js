import React, { useEffect, useState } from 'react';
import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet';

const MapComponent = () => {
    const [recipes, setRecipes] = useState([]);

    useEffect(() => {
        // Fetch recipe data from the backend
        const fetchRecipes = async () => {
            try {
                const response = await fetch('http://localhost:8080/api/recipes');
                const data = await response.json();
                setRecipes(data);
            } catch (error) {
                console.error('Error fetching recipes:', error);
            }
        };

        fetchRecipes();
    }, []);

    return (
        <MapContainer center={[51.505, -0.09]} zoom={2} style={{ height: '100vh', width: '100%' }}>
            <TileLayer
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            />
            {recipes.map((recipe, index) => {
                // Check for valid latitude and longitude
                const latitude = parseFloat(recipe.latitude);
                const longitude = parseFloat(recipe.longitude);

                if (isNaN(latitude) || isNaN(longitude)) {
                    console.warn(`Skipping recipe with invalid location: ${recipe.name}`);
                    return null; // Skip this marker if coordinates are invalid
                }

                return (
                    <Marker key={index} position={[latitude, longitude]}>
                        <Popup>
                            <strong>{recipe.name}</strong><br />
                            {recipe.description}<br />
                            Ingredients: {recipe.ingredients ? recipe.ingredients.join(', ') : 'N/A'}
                        </Popup>
                    </Marker>
                );
            })}
        </MapContainer>
    );
};

export default MapComponent;

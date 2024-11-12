import React, { useState } from 'react';
import './RecipeForm.css';

const RecipeForm = ({ latitude, longitude, onRecipeSubmit }) => {
    const [isVisible, setIsVisible] = useState(false);
    const [formData, setFormData] = useState({
        name: '',
        description: '',
        ingredients: '',
        userId: ''
    });

    const openForm = () => {
        setIsVisible(true);
        console.log("Form opened with latitude:", latitude, "and longitude:", longitude);
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const recipeData = {
            ...formData,
            ingredients: formData.ingredients.split(',').map((ingredient) => ingredient.trim()),
            latitude,
            longitude
        };

        try {
            const response = await fetch('http://localhost:8080/api/recipes', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(recipeData),
            });

            if (response.ok) {
                const data = await response.json();
                console.log('Recipe submitted successfully:', data);
                alert('Recipe submitted successfully!');

                // Trigger the callback to refresh recipes in MapComponent
                onRecipeSubmit();
            } else {
                console.error('Failed to submit recipe:', response.statusText);
                alert('Failed to submit recipe. Please try again.');
            }
        } catch (error) {
            console.error('Error submitting recipe:', error);
            alert('An error occurred while submitting the recipe.');
        }

        // Reset the form and close it
        setFormData({ name: '', description: '', ingredients: '', userId: '' });
        setIsVisible(false);
    };

    return (
        <>
            <button onClick={openForm} className="open-form-button">Add Recipe</button>

            {isVisible && (
                <div className="form-overlay">
                    <div className="form-container">
                        <button onClick={() => setIsVisible(false)} className="close-form-button">&times;</button>
                        <h2>Submit a Recipe</h2>
                        <form onSubmit={handleSubmit}>
                            <label>
                                Name:
                                <input type="text" name="name" value={formData.name} onChange={handleChange} required />
                            </label>
                            <label>
                                Description:
                                <textarea name="description" value={formData.description} onChange={handleChange} required></textarea>
                            </label>
                            <label>
                                Ingredients (comma separated):
                                <input type="text" name="ingredients" value={formData.ingredients} onChange={handleChange} required />
                            </label>
                            <label>
                                Latitude:
                                <input type="text" value={latitude || ''} readOnly className="display-only" />
                            </label>
                            <label>
                                Longitude:
                                <input type="text" value={longitude || ''} readOnly className="display-only" />
                            </label>
                            <label>
                                User ID:
                                <input type="number" name="userId" value={formData.userId} onChange={handleChange} required />
                            </label>
                            <button type="submit">Submit</button>
                        </form>
                    </div>
                </div>
            )}
        </>
    );
};

export default RecipeForm;

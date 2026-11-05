<#import "fragments/navbar.ftlh" as layout>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Marvel Character</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<@layout.navbar/>

<main>
    <h2>Edit Character</h2>

    <form action="/characters/${character.id?c}/edit"
          method="post">

        <label for="name">Character Name:</label>
        <input type="text" id="name" name="name"
               value="${character.name!''}" required>

        <label for="alias">Alias:</label>
        <input type="text" id="alias" name="alias"
               value="${character.alias!''}" required>

        <label for="universe">Universe:</label>
        <input type="text" id="universe" name="universe"
               value="${character.universe!''}" required>

        <label for="description">Description:</label>
        <textarea id="description" name="description"
                  rows="5" required>${character.description!''}</textarea>

        <label for="imageUrl">Image URL:</label>
        <input type="url" id="imageUrl" name="imageUrl"
               value="${character.imageUrl!''}">

        <button type="submit">Save Changes</button>
    </form>

    <a href="/characters/${character.id?c}">
        Cancel
    </a>
</main>
</body>
</html>

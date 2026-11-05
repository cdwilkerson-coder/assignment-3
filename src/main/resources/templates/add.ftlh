<#import "fragments/navbar.ftlh" as layout>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Marvel Character</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <@layout.navbar/>

    <main>
        <h2>Add a New Character</h2>

        <form action="/characters" method="post">

            <label for="name">Character Name:</label>
            <input type="text" id="name" name="name"
                   required maxlength="100">

            <label for="alias">Alias:</label>
            <input type="text" id="alias" name="alias"
                   required maxlength="100">

            <label for="universe">Universe:</label>
            <input type="text" id="universe" name="universe"
                   required maxlength="100">

            <label for="description">Description:</label>
            <textarea id="description" name="description"
                      required rows="5"></textarea>

            <label for="imageUrl">Image URL:</label>
            <input type="url" id="imageUrl" name="imageUrl"
                   placeholder="https://example.com/image.jpg"
                   required>

            <button type="submit">Add Character</button>
        </form>

        <a href="/">Back to Gallery</a>
    </main>
</body>
</html>

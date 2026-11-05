<#import "fragments/navbar.ftlh" as layout>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About - Marvel Character Gallery</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<header>
    <h1>Marvel Character Gallery</h1>
    <nav>
        <a href="/">Home</a>
        <a href="/about">About</a>
        <a href="/characters/new">Add a Character</a>
    </nav>
</header>

<main>
    <h2>About Marvel Character Gallery</h2>

    <p>
        Welcome to Marvel Character Gallery! This web application
        allows users to explore, search, and manage their favorite
        Marvel characters in one place.
    </p>

    <p>
        Users can browse a collection of Marvel heroes and villains,
        view detailed character profiles, add new characters,
        update existing information, and remove characters
        from the gallery.
    </p>

    <img
        src="https://upload.wikimedia.org/wikipedia/commons/b/b9/Marvel_Logo.svg"
        alt="Marvel Logo"
        style="max-width:300px;width:100%;height:auto;"
    >

    <h2>How This Application Works</h2>

    <p>
        This application uses Spring Boot and FreeMarker MVC
        to generate dynamic HTML pages. The page controller
        handles browser requests and communicates with
        the service layer to retrieve or modify character data.
    </p>

    <p>
        The service layer uses Spring Data JPA to communicate
        with a PostgreSQL database hosted on Neon.
        This allows character information to remain saved
        even after the application is restarted.
    </p>

    <p>
        The application also includes REST API endpoints
        that return character data in JSON format.
        The frontend and REST API share the same
        service layer and database.
    </p>

    <a href="/">Back to Gallery</a>
</main>

</body>
</html>

![CI](https://github.com/byteleaf/companyon-frontend/workflows/CI/badge.svg) [![codecov](https://codecov.io/gh/byteleaf/companyon-frontend/branch/master/graph/badge.svg)](https://codecov.io/gh/byteleaf/companyon-frontend) [![Codacy Badge](https://app.codacy.com/project/badge/Grade/7585465da2344ebfbfbe8050bef5ac03)](https://www.codacy.com/gh/byteleaf/companyon-frontend/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=byteleaf/companyon-frontend&amp;utm_campaign=Badge_Grade)

## Required steps

Copy `.env.local.sample` and name it `.env.local`

### Setup auth0

- Login into [auth0](https://auth0.com/) and create a new (SPA) Application or select an existing one.
    - Update the `.env.local`
        - Copy the your domain and enter it as `REACT_APP_AUTH_DOMAIN` (e.g. `REACT_APP_AUTH_DOMAIN=mytest.eu.auth0.com`)
        - Copy the Client ID and enter it as `REACT_APP_AUTH_CLIENT_ID` (e.g. `REACT_APP_AUTH_CLIENT_ID=1234567890abcdef`)
    - Fill out the Application URIs:
        - Allowed Callback URLs: `http://localhost:3000/`
        - Allowed Logout URLs: `http://localhost:3000/`
        - Allowed Web Origins: `http://localhost:3000`
- Create a new API application in auth0.
  - As an identifier either use `https://companyon.io/graphql` or select your own. When you want to use a different one, don't forget to update `REACT_APP_AUTH_AUDIENCE` in your `.env.local`.

#### Google Auth

By default Auth0 will use development keys to connect with the google authentication service. These keys don't persist login information so you will need to register a Google App and connect it to your Auth0 account following [these steps](https://auth0.com/docs/connections/social/google).

After creating the app you will get a `Client ID` and a `Client Secret`. Add both of them into your [Auth0 Google Social connection](https://auth0.com/docs/get-started/dashboard/set-up-social-connections).

## Dependencies

This project uses `NPM` as the preferred package manager.

To install dependencies you can run:
```bash
npm install
```

## Available Scripts

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.<br />
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

The page will reload if you make edits.<br />
You will also see any lint errors in the console.

### `npm run test`

Launches the test runner in the interactive watch mode.<br />
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm run build`

Builds the app for production to the `build` folder.<br />
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.<br />
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

### `npm run eject`

**Note: this is a one-way operation. Once you `eject`, you can’t go back!**

If you aren’t satisfied with the build tool and configuration choices, you can `eject` at any time. This command will remove the single build dependency from your project.

Instead, it will copy all the configuration files and the transitive dependencies (webpack, Babel, ESLint, etc) right into your project so you have full control over them. All of the commands except `eject` will still work, but they will point to the copied scripts so you can tweak them. At this point you’re on your own.

You don’t have to ever use `eject`. The curated feature set is suitable for small and middle deployments, and you shouldn’t feel obligated to use this feature. However we understand that this tool wouldn’t be useful if you couldn’t customize it when you are ready for it.

## Learn More

You can learn more in the [Create React App documentation](https://facebook.github.io/create-react-app/docs/getting-started).

To learn React, check out the [React documentation](https://reactjs.org/).

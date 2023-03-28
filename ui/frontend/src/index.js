import React, {StrictMode} from "react";
import {createRoot} from "react-dom/client";
import "./index.css";
import App from "./App";
import "bootstrap/dist/css/bootstrap.min.css";
import reportWebVitals from "./reportWebVitals";

const rootElement = document.getElementById("root");
const root = createRoot(rootElement);

root.render(
    <App/>
);
reportWebVitals();
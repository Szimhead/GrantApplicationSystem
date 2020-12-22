import React from 'react';

import { Header } from './components/header';
import { Footer } from './components/footer';
import {Jumbotron} from "./components/jumbotron";


export const App = () => {
  return (
      <>
        <Header />
        <Jumbotron/>
        <Footer />
      </>
  );
};

export default App;

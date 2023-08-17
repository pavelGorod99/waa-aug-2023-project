import axios from 'axios';
import ReactEcharts from 'echarts-for-react';
import { useEffect, useState } from 'react';

const Dashboard = () => {

  const [cityData, setCityData] = useState('');
  const [stateData, setStateData] = useState('');

  useEffect(() => {

    fetchData();

  }, []);

  const fetchData = async () => {

    try {

      const response1 = await axios.get('/auth/studentsPerState');

      setCityData(response1.data);

      const response2 = await axios.get('/auth/studentsPerCity');

      setStateData(response2.data);

    } catch (error) {

      console.error('Error fetching data:', error);

    }

  };

  const cities = [];

  const studentPerCityCounter = [];

  const states = [];

  const studentPerStateCounter = [];


  if (cityData && stateData) {

    cityData.forEach(entry => {

      const [city, count] = Object.entries(entry)[0]; // Get the first key-value pair

      cities.push(city); // Push the city into the cities array

      studentPerCityCounter.push(count); // Push the count into the studentCounts array

    });
    
    stateData.forEach(entry => {

      const [state, count] = Object.entries(entry)[0]; // Get the first key-value pair

      states.push(state); // Push the state into the states array

      studentPerStateCounter.push(count); // Push the count into the studentCounts array

    });

  }


  const cityOption = {

    title: {

      text: 'Number of Students in Each City',

    },

    tooltip: {},

    xAxis: {

      data: cities,

    },

    yAxis: {},

    series: [

      {

        name: 'Student Count',

        type: 'bar',

        data: studentPerCityCounter,

      },

    ],

  };

  const stateOption = {

    title: {

      text: 'Number of Students in Each State',

    },

    tooltip: {},

    xAxis: {

      data: states,

    },

    yAxis: {},

    series: [

      {

        name: 'Student Count',

        type: 'bar',

        data: studentPerStateCounter,

      },

    ],

  };


  return ( 
    <>
      <ReactEcharts option={cityOption} style={{ height: '400px' }} />
      <br />
      <ReactEcharts option={stateOption} style={{ height: '400px' }} />
    </>
  );
}

export default Dashboard;

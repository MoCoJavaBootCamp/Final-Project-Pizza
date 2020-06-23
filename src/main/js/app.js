const React = require('react');
const ReactDOM = require('react-dom');

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {employees: []};
  }

  componentDidMount() {
    fetch("/")
      .then(response => response.json())
      .then(data => console.log(data));
  }

  render() { (3)
		return (
			<EmployeeList employees={this.state.employees}/>
		)
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)
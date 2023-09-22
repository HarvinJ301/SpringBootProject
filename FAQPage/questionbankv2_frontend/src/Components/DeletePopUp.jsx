import '../Styles/DeletePopUp.css'

const DeletePopUp = (props) => {
    return (
      <div className='deletePopUps'>
        <div className='deletePopUpText'>
          <p>Are you sure you want to delete this question?</p>
          <button onClick={props.handleDeleteFalse} className= 'cancelButton' title='Cancel Delete'>Cancel</button>
          <button onClick={props.handleDeleteTrue} className = 'confirmButton' title='Confirm Deletion of Question'>
            Confirm
          </button>
        </div>
      </div>
    );
  }

export default DeletePopUp  
import '../Styles/EditPopUp.css'

const EditPopUp = (props) => {
  return (
    <div className="editPopUp">
        <div className="editPopUpText">
          <p>Are you sure you want to discard your changes?</p>
          <button onClick={props.handleEditFalse} className= 'cancelButton'>Cancel</button>
          <button onClick={props.handleEditTrue} className = 'confirmButton'>
            Confirm
          </button>
        </div>

    </div>
  );
}

export default EditPopUp
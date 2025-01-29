package gr.aueb.cf.mobilecontacts.service;

import gr.aueb.cf.mobilecontacts.dao.IMobileContactDAO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactInsertDTO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactUpdateDTO;
import gr.aueb.cf.mobilecontacts.exceptions.ContactNotFoundException;
import gr.aueb.cf.mobilecontacts.exceptions.PhoneNumberAlreadyExistException;
import gr.aueb.cf.mobilecontacts.mapper.Mapper;
import gr.aueb.cf.mobilecontacts.model.MobileContact;

import java.util.List;

public class MobileContactServiceImpl implements IMobileContactService {

    private final IMobileContactDAO dao;

    public MobileContactServiceImpl(IMobileContactDAO dao) {
        this.dao = dao;
    }

    @Override
    public MobileContact insertMobileContact(MobileContactInsertDTO dto)
            throws PhoneNumberAlreadyExistException {
        MobileContact mobileContact;

        try {
            if (dao.phoneNumberExists(dto.getPhoneNumber())) {
                throw new PhoneNumberAlreadyExistException("Contact with phone number " + dto.getPhoneNumber() + "already exists.");
            }
            mobileContact = Mapper.mapInsertDTOToContact(dto);

            System.err.printf("MobileContactServiceImpl Logger: %s was inserted", mobileContact);
            return dao.insert(mobileContact);

        } catch (PhoneNumberAlreadyExistException e) {
            System.err.printf("MobileContactServiceImpl Logger: contact with phone number: %s already exists.\n", dto.getPhoneNumber());
            throw e;
        }
    }

    @Override
    public MobileContact updateMobileContact(MobileContactUpdateDTO dto)
            throws PhoneNumberAlreadyExistException, ContactNotFoundException {
        MobileContact mobileContact;
        MobileContact newContact;
        try {

            if (!dao.userIdExists(dto.getId())) {
                throw new ContactNotFoundException("Contact with id: " + dto.getId() + "Not Found for update");
            }

            mobileContact = dao.getById(dto.getId());
            boolean isPhoneNumberOurOwn = mobileContact.getPhoneNumber().equals(dto.getPhoneNumber());
            boolean isPhoneNumberExists = dao.phoneNumberExists(dto.getPhoneNumber());

            if (isPhoneNumberExists && !isPhoneNumberOurOwn) {
                throw new PhoneNumberAlreadyExistException("Contact with phone number: " + dto.getPhoneNumber() + "already exists and can not be updated.");
            }

            newContact = Mapper.mapUpdateDTOToContact(dto);
            System.err.printf("MobileContactServiceImpl Logger: %s was updated with new info: %s", mobileContact, newContact);
            return dao.update(dto.getId(), newContact);

        } catch (ContactNotFoundException | PhoneNumberAlreadyExistException e) {
            System.err.printf("MobileContactServiceImpl Logger: %s\n", e.getMessage());
            throw e;
        }

    }

    @Override
    public void deleteContactById(Long id) throws ContactNotFoundException {
        try {
            if (!dao.userIdExists(id)) {
                throw new ContactNotFoundException("contact with id: " + id + " not found for delete");
            }

            System.err.printf("MobileContactServiceImpl Logger: contact with %d: was deleted.\n", id);
            dao.deleteById(id);
        } catch (ContactNotFoundException e) {
            System.err.printf("MobileContactServiceImpl Logger: %s|n ", e.getMessage());
            throw e;
        }
    }

    @Override
    public MobileContact getContactById(Long id) throws ContactNotFoundException {
        MobileContact mobileContact;

        try {
            mobileContact = dao.getById(id);
            if (mobileContact == null) {
                throw new ContactNotFoundException("Contact with id: " + id + "Not found");
            }
            return mobileContact;
        } catch (ContactNotFoundException e) {
            System.err.printf("Contact with id: %d was not found to get returned", id);
            throw e;
        }
    }

    @Override
    public List<MobileContact> getAllContacts() {
        return dao.getAll();
    }

    @Override
    public MobileContact getContactByPhoneNumber(String phoneNumber) throws ContactNotFoundException {
        MobileContact mobileContact;

        try {
            mobileContact = dao.getByPhoneNumber(phoneNumber);
            if (mobileContact == null) {
                throw new ContactNotFoundException("Contact with id: " + phoneNumber + "Not found");
            }
            return mobileContact;
        } catch (ContactNotFoundException e) {
            System.err.printf("Contact with phone number: %s was not found to get returned\n", phoneNumber);
            throw e;
        }
    }

    @Override
    public void deleteContactByPhoneNumber(String phoneNumber) throws ContactNotFoundException {
        try {
            if (!dao.phoneNumberExists(phoneNumber)) {
                throw new ContactNotFoundException("contact with phone number: " + phoneNumber + " not found for delete");
            }

            System.err.printf("MobileContactServiceImpl Logger: contact with phone number: %s was deleted.\n", phoneNumber);
            dao.deleteByPhoneNumber(phoneNumber);
        } catch (ContactNotFoundException e) {
            System.err.printf("MobileContactServiceImpl Logger: %s|n ", e.getMessage());
            throw e;
        }
    }

    //Mapping
//    private MobileContact mapInsertDTOToContact(MobileContactInsertDTO dto) {
//        return new MobileContact(null, dto.getFirstname(), dto.getLastname(), dto.getPhoneNumber());
//    }
//
//    private MobileContact mapUpdateDTOToContact(MobileContactUpdateDTO dto) {
//        return new MobileContact(dto.getId(), dto.getFirstname(), dto.getLastname(), dto.getPhoneNumber());
//    }
}

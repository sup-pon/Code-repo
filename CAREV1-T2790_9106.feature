#Author: Priyanka.Pandey@osi.ca.gov
#Date Created: 06/14/2024
#Date modified: 06/14/2024
@milestone14
Feature: Case Management: Engagement & Services_Validate that the CM Worker and CM Supervisor must be able to receive and upload visitation feedback received from visitation participants and substitute care provider.

  Background: 
    Given Application is in Login Page

  @T2790
  Scenario Outline: CARESV1-2790_9106_SIT_Case Management: Engagement & Services_Validate that the CM Worker and CM Supervisor must be able to receive and upload visitation feedback received from visitation participants and substitute care provider.
    When i logged in as <HotlineUser> for V1 application
    And verify i am on Home page
    And i navigate to Screenings page
    And i click 'New' button
    #########################STEP 1 and 2 Pre-Condition#######################
    ###  Initial Screening Information  ####################################
    And i enter Initial screening information details with Caller Type as Non Mandated Reporter
      | reasonForCall          | safelySurrenderedBaby | callBack |
      | Abuse/Neglect Referral | No                    | No       |
    And i click Save and Proceed on Initial Screening Information step
    And i set page to view screening
    And i capture SCR ID from view screening page
    And i capture screening url from view screening page
    ####  Add Person(s)  #############################
    And i enter person 1 deatils with role as 'Alleged Victim' in Screening
    And i scroll to Down by 200 pixels
    And i click '+ Add Row' button
    And i scroll to Down by 200 pixels
    And i enter person 2 deatils with role as 'Alleged Perpetrator' in Screening
    And i click Save and Proceed on Add Persons step
    ####  Add Addresss(es)  #############################
    And i scroll on page to Address Type dropdown
    And i enter address with Location of Children as address type
    And i click Save and Proceed on Add Addresses step
    And i scroll to Up by 600 pixels
    And i set page to view screening
    ####  Add Allegation(s) #############################
    And i select stored 'Alleged Victim' value from constant file in Select Alleged Victim dropdown
    And i select stored 'Alleged Perpetrator' value from constant file in Select Alleged Perpetrator dropdown
    And i select 'Caretaker Absence/Incapacity' value in Allegation Type dropdown
   	And i click 'Abandonment' link
   	And i click 'Move selection to Selected' button
   	And i click Save and Proceed on Add Allegations step
   	   ##################  Identify Safety Alert(s)  #############################
    And i click Save and Proceed on Identify Safety Alerts step
    And i refresh the page
    ##################  SDM Hotline Tool  #############################
    And i select 'Immediate' value in County Final Determination dropdown
    And i click Save and Proceed on SDM Hotline Tool step
    And i refresh the page
    #####  Tribal Inquiry & Collaboration  #############################
    And i enter details on Tribal Inquiry and Collaboration step
    And i click Save and Proceed on Tribal Inquiry & Collaboration step
    ####  +ERR Doc  #############################
    And i generate ERR document
    ####  Supervisor Review/Promotion  #############################
    And i scroll to down by 200 pixels
    And i set page to view screening
    And i enter and select 'Hotline Supervisor ContraCosta' value from property file in Approval Supervisor searchbox
    And i click Save and Proceed
    ####  Validate screening person - 1  #################
    And i scroll to down by 300 pixels
    And i click on 'Screening Persons' Partial link
    And i refresh the page
    And i capture record number 1 of Screening Person column from related Screening Persons table of Screening Persons page
    And i click saved record of Screening Person column from Screening Persons page
    And i refresh the page
    And i click 'Validate Person' button
    And i click 'Search' button
    And i click 'New Person' button
    And i select 'Male' value in Sex at Birth dropdown
    And i click 'Save' button
    And i verify 'Screening Person record has been attached' toast message
    Then i navigate to current screening using url
    ######  Validate screening person - 2 ##################
    And i refresh the page
    And i scroll to down by 1000 pixels
    And i click on 'Screening Persons' Partial link
    And i capture record number 2 of Screening Person column from related Screening Persons table of Screening Persons page
    And i click saved record of Screening Person column from Screening Persons page
    And i refresh the page
    And i click 'Validate Person' button
    And i click 'Search' button
    And i click 'New Person' button
    And i select 'Male' value in Sex at Birth dropdown
    And i click 'Save' button
    And i verify 'Screening Person record has been attached' toast message
    Then i navigate to current screening using url
    #######  Submit For Approval  #############################
    And i navigate to Screenings page
    Then i navigate to current screening using url
    And i click 'Submit For Approval' button
    And i refresh the page
    And i enter 'Submitting for approval' in Comments textarea
    And i click Submit
    And i verify 'Screening has been submitted for approval.' text is present on page
    And i click 'Close' button
    And i click on Log Out
    ##########  Approval  #####################################
    When i logged in as <HotlineSupervisor> for V1 application
    And i refresh the page
    Then i click on the notification for SCR ID to approve
    And i verify 'Approve' text is present on page
    Then i Approve the approval request
    And i set page to view approval
    And i refresh the page
    And i verify 'Approved' text is present on page
    And i verify 'Approved' text for Status element
    And i click on Log Out
    ###########  Assignment  ###################################
    When i logged in as <HotlineUser> for V1 application
    Then i navigate to current screening using url
    And i refresh the page
    And i verify 'Primary Worker' searchbox is present on page
    And i enter and select 'erworker contracosta' value from property file in Primary Worker searchbox
    And i click Save
    And i refresh the page
    And i set page to view screening
    And i capture Folio ref from view screening page
    And i capture Folio ref url from view screening page
    ###############  Logging in as user to verify  #############################
    And i click on Log Out
    When i logged in as <ERUser> for V1 application
    Then i navigate to current folio using url
    And i set page to view folio
    ############### Update Allegation  #############################
    And i click on 'Allegations' Partial link
    And i refresh the page
    And i click first Allegation ID from related Allegations table of Allegations page
    Then i verify 'Allegation Conclusion' dropdown is present on page
    Then i verify 'Allegation Conclusion Rationale' textbox is present on page
    And i scroll to down by 200 pixels
    And i select 'Unfounded' value in Allegation Conclusion dropdown
    And i enter 'randomText' in Allegation Conclusion Rationale textbox
    And i scroll to down by 200 pixels
    And i click Save
    ############### Update Allegation twice  #############################
    Then i navigate to current folio using url
    And i refresh the page
    And i set page to view folio
    And i click on 'Allegations' Partial link
    And i refresh the page
    And i click first Allegation ID from related Allegations table of Allegations page
    Then i verify 'Allegation Conclusion' dropdown is present on page
    Then i verify 'Allegation Conclusion Rationale' textbox is present on page
    And i scroll to down by 200 pixels
    And i select 'Unfounded' value in Allegation Conclusion dropdown
    And i enter 'randomText' in Allegation Conclusion Rationale textbox
    And i scroll to down by 200 pixels
    And i click Save
    ################ Update Disposition #############################
    Then i navigate to current folio using url
    And i set page to view folio
    And i click on 'Disposition' Partial link
    And i refresh the page
    And i click first Disposition ID from related Disposition table of Disposition page
    And i set page to view disposition
    And i scroll to down by 200 pixels
    And i click 'Edit Closure Date' button
    And i enter future date in 'MM/dd/YYYY' format for -2 days from today in Closure Date textbox
    And i select 'Promote to a Case' value in Disposition dropdown
    And i enter 'randomText' in Rationale textarea
    And i click Save
    ################# + Contact - Contact  #############################
    And i refresh the page
    Then i navigate to current folio using url
    And i set page to view folio
    And i add new structured investigation contact
      | location | contactStatus |
      | Home     | Completed     |
    And i refresh the page
    Then i navigate to current folio using url
    ####################  Submit Folio for Approval  ##########################################
    And i set page to view folio
    And i click 'Approval/Audit' subtab
    Then i verify 'Edit Approval Supervisor' button is present on page
    And i click 'Edit Approval Supervisor' button
    And i enter and select 'ersupervisor contracosta' value from property file in Approval Supervisor searchbox
    And i click Save
    And i click 'Show more actions' button
    And i click 'Submit for Approval' button
    And i click 'Yes' button
    And i click Submit
    And i click on Log Out
    ################ Folio Approval  #####################################
    When i logged in as 'ERSupervisorContraCosta' for V1 application
    Then i navigate to current folio using url
    And i click 'Approval/Audit' subtab
    And i scroll to down by 300 pixels
    And i click 'Edit Investigation Closed Date' button
    And i enter 'TodayDate' in Investigation Closed Date textbox
    And i click Save
    And i verify 'Approve' button is present on page
    Then i Approve the approval request
    And i set page to view approval
    And i refresh the page
    Then i verify 'Approved' text is present on page
    Then i verify 'Promote to Case' button is present on page
    And i click 'Promote to Case' button
    And i select 'Child Welfare Voluntary' value in Case Type dropdown
    And i select index position 'Emergency Response' from Service Component inputdropdown
    And i select index position 'Child Welfare' from Primary Responsible Agency inputdropdown
    And i enter and select 'cm worker contracosta' value from property file in Primary Worker searchbox
    And i click Save
    And i refresh the page
    And i capture current url of record type folio ref
    And i click on Log Out
    ######  Login as CM User  ####################
    When i logged in as <CMUser> for V1 application
    And i click 'Notifications' button
    Then i navigate to current folio using url
    And i set page to view folio
    And i click 'Related Folios' subtab
    And i scroll to Down by 200 pixels
    And i set page to view folio
    And i click 'Associated folio ref' element
    And i capture Folio ref url from view folio page
    And i click on Log Out
    ###################  Logging in as Secondary Worker  #################
    When i logged in as 'ERSupervisorContraCosta' for V1 application
    And verify i am on Home page
    Then i navigate to current folio using url
    And i set page to view folio
    ################  Assignments- Secondary Worker ##########################################
    And i click on 'Assignments' Partial link
    And i click 'New' link
    And i enter 'TodayFullDate' in Start Date textbox
    And i enter and select 'cm supervisor contracosta' value from property file in Person searchbox
    And i select 'Court Worker' value in Worker Role dropdown
    And i click Save
    And i click on Log Out
    ########################### Logging in as CM Worker #############################################
    #################################################################################################
    When i logged in as <CMUser> for V1 application
    And verify i am on Home page
    Then i navigate to current folio using url
    And i set page to view folio
    ########### Add Visitation Determination #######
    And i create new visitation determination record
    ############ Approve Visitation Determination for Visitation Log Creation ##########
    Then i navigate to current folio using url
    And i set page to view folio
    And i click 'Visitation' subtab
    And i scroll to Down by 300 pixels
    And i click 'Visitation Determination ID' at row number 1
    And i click 'Edit Visitation Type' button
    And i enter 'randomText' in Relationship to Child textbox
    And i enter '4657567687' in Phone Number textbox
    And i select 'Yes' value in Approved to Visit? dropdown
    And i enter 'randomText' in Next Steps to Liberalize Visitation textarea
    And i enter 'randomText' in Visitation Goals textarea
    And i scroll to Down by 100 pixels
    And i move Child Welfare Office values from Locations available list to chosen list
    And i scroll to Up by 300 pixels
    And i click 'Court & Monitoring Documentation' link
    And i select 'Observed' value in Level of Monitoring dropdown
    And i click 'Visitation Schedule' link
    And i move Monday values from Day/s of the Week available list to chosen list
    And i scroll to Down by 200 pixels
    And i enter 'TodayDate' in Start Date textbox
    And i enter 'TodayDate' in End Date textbox
    And i scroll to Down by 100 pixels
    And i move Park values from Preferred Location for Visit available list to chosen list
    And i click Save
    ############### Step 3- 6 Create Visitation Log #######################
    And i click 'Visitation' subtab
    And i scroll to Down by 300 pixels
    And i verify 'Visit Log' textContains is present on page
    And i verify 'New' button is present on page
    And i click 'New' button
    And i enter 'Today' in Date of Visit textbox
    And i click Next
    And i select 'foliopersonallCheckbox' checkbox
    And i click Next
    And i click Complete
    ######## Step 7-10 Documents update ########################(Step 10 error not coming to be removed )
    And i click 'Visitation' subtab
    And i scroll to Down by 300 pixels
    And i verify 'Visit ID' textContains is present on page
    And i click 'Visit ID' at row number 1
    And i click 'Documents' link
    And i click 'Upload' button
    ######Step 11- 22 #####################
    And i verify Document Category dropdown value are Visitation
    And i verify 'Upload' button is disabled
    And i select 'Visitation' value in Document Category dropdown
    And i select 'Feedback' value in Document Type dropdown
    And i select 'Final' value in Document Status dropdown
    And i uploaded the file with name 'test-image.jpg'
    And i click Cancel
    And i verify 'Document Category' text is not present on page
    And i click 'Upload' button
    And i select 'Visitation' value in Document Category dropdown
    And i select 'Feedback' value in Document Type dropdown
    And i select 'Final' value in Document Status dropdown
    And i enter 'randomText' in Description textarea
    And i uploaded the file with name 'testFile.pdf'
    Then i verify column names of Document List section on view folio page
      | Title | Document Category | Document Type | Upload Date | Upload By | Description |

    Examples: 
      | HotlineUser        | ERUser                | HotlineSupervisor       | PrimaryWorker           | CMUser                |
      | 'StaffContraCosta' | 'ERWorkerContraCosta' | 'SupervisorContraCosta' | 'cm worker contracosta' | 'CMWorkerContraCosta' |

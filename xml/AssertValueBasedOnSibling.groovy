/**
* Refer: https://stackoverflow.com/questions/41389510/how-to-assert-that-a-node-value-is-associated-with-another-node-value/41390196#41390196
*
**/

def results = """<Results>
   <ResultSet fetchSize="128">
      <Row rowNumber="1">
         <REGISTRATIONKEY>1644606</REGISTRATIONKEY>
         <WORKFLOWACTIVITY>Fraud Identity Validation (NegMatch)</WORKFLOWACTIVITY>
         <WORKFLOWACTIVITYKEY>3</WORKFLOWACTIVITYKEY>
         <WORKFLOWACTIVITYSTATUSKEY>2</WORKFLOWACTIVITYSTATUSKEY>
         <WORKFLOWACTIVITYSTATUS>Passed</WORKFLOWACTIVITYSTATUS>
         <WORKFLOWPROCESSKEY>1</WORKFLOWPROCESSKEY>
         <WORKFLOWPROCESSSTATUSKEY>1</WORKFLOWPROCESSSTATUSKEY>
         <WORKFLOWPROCESS>CardAccountRegistration</WORKFLOWPROCESS>
         <WORKFLOWPROCESSSTATUS>Started</WORKFLOWPROCESSSTATUS>
         <CREATEDATE>2016-12-29 15:29:25.3</CREATEDATE>
      </Row>
      <Row rowNumber="2">
         <REGISTRATIONKEY>1644606</REGISTRATIONKEY>
         <WORKFLOWACTIVITY>Age Verification</WORKFLOWACTIVITY>
         <WORKFLOWACTIVITYKEY>6</WORKFLOWACTIVITYKEY>
         <WORKFLOWACTIVITYSTATUSKEY>2</WORKFLOWACTIVITYSTATUSKEY>
         <WORKFLOWACTIVITYSTATUS>Passed</WORKFLOWACTIVITYSTATUS>
         <WORKFLOWPROCESSKEY>1</WORKFLOWPROCESSKEY>
         <WORKFLOWPROCESSSTATUSKEY>1</WORKFLOWPROCESSSTATUSKEY>
         <WORKFLOWPROCESS>CardAccountRegistration</WORKFLOWPROCESS>
         <WORKFLOWPROCESSSTATUS>Started</WORKFLOWPROCESSSTATUS>
         <CREATEDATE>2016-12-29 15:29:25.3</CREATEDATE>
      </Row>
      <Row rowNumber="3">
         <REGISTRATIONKEY>1644606</REGISTRATIONKEY>
         <WORKFLOWACTIVITY>Address Validation</WORKFLOWACTIVITY>
         <WORKFLOWACTIVITYKEY>2</WORKFLOWACTIVITYKEY>
         <WORKFLOWACTIVITYSTATUSKEY>2</WORKFLOWACTIVITYSTATUSKEY>
         <WORKFLOWACTIVITYSTATUS>Passed</WORKFLOWACTIVITYSTATUS>
         <WORKFLOWPROCESSKEY>1</WORKFLOWPROCESSKEY>
         <WORKFLOWPROCESSSTATUSKEY>1</WORKFLOWPROCESSSTATUSKEY>
         <WORKFLOWPROCESS>CardAccountRegistration</WORKFLOWPROCESS>
         <WORKFLOWPROCESSSTATUS>Started</WORKFLOWPROCESSSTATUS>
         <CREATEDATE>2016-12-29 15:29:25.3</CREATEDATE>
      </Row>
      <Row rowNumber="4">
         <REGISTRATIONKEY>1644606</REGISTRATIONKEY>
         <WORKFLOWACTIVITY>Card Limit Validation</WORKFLOWACTIVITY>
         <WORKFLOWACTIVITYKEY>4</WORKFLOWACTIVITYKEY>
         <WORKFLOWACTIVITYSTATUSKEY>2</WORKFLOWACTIVITYSTATUSKEY>
         <WORKFLOWACTIVITYSTATUS>Passed</WORKFLOWACTIVITYSTATUS>
         <WORKFLOWPROCESSKEY>1</WORKFLOWPROCESSKEY>
         <WORKFLOWPROCESSSTATUSKEY>1</WORKFLOWPROCESSSTATUSKEY>
         <WORKFLOWPROCESS>CardAccountRegistration</WORKFLOWPROCESS>
         <WORKFLOWPROCESSSTATUS>Started</WORKFLOWPROCESSSTATUS>
         <CREATEDATE>2016-12-29 15:29:25.3</CREATEDATE>
      </Row>
      <Row rowNumber="5">
         <REGISTRATIONKEY>1644606</REGISTRATIONKEY>
         <WORKFLOWACTIVITY>Customer Identity Validation (CIP)</WORKFLOWACTIVITY>
         <WORKFLOWACTIVITYKEY>5</WORKFLOWACTIVITYKEY>
         <WORKFLOWACTIVITYSTATUSKEY>3</WORKFLOWACTIVITYSTATUSKEY>
         <WORKFLOWACTIVITYSTATUS>Failed</WORKFLOWACTIVITYSTATUS>
         <WORKFLOWPROCESSKEY>1</WORKFLOWPROCESSKEY>
         <WORKFLOWPROCESSSTATUSKEY>1</WORKFLOWPROCESSSTATUSKEY>
         <WORKFLOWPROCESS>CardAccountRegistration</WORKFLOWPROCESS>
         <WORKFLOWPROCESSSTATUS>Started</WORKFLOWPROCESSSTATUS>
         <CREATEDATE>2016-12-29 15:29:25.503</CREATEDATE>
      </Row>
      <Row rowNumber="6">
         <REGISTRATIONKEY>1644606</REGISTRATIONKEY>
         <WORKFLOWACTIVITY>Out Of Wallet Quiz</WORKFLOWACTIVITY>
         <WORKFLOWACTIVITYKEY>11</WORKFLOWACTIVITYKEY>
         <WORKFLOWACTIVITYSTATUSKEY>4</WORKFLOWACTIVITYSTATUSKEY>
         <WORKFLOWACTIVITYSTATUS>Skipped</WORKFLOWACTIVITYSTATUS>
         <WORKFLOWPROCESSKEY>1</WORKFLOWPROCESSKEY>
         <WORKFLOWPROCESSSTATUSKEY>1</WORKFLOWPROCESSSTATUSKEY>
         <WORKFLOWPROCESS>CardAccountRegistration</WORKFLOWPROCESS>
         <WORKFLOWPROCESSSTATUS>Started</WORKFLOWPROCESSSTATUS>
         <CREATEDATE>2016-12-29 15:29:25.613</CREATEDATE>
      </Row>
      <Row rowNumber="7">
         <REGISTRATIONKEY>1644606</REGISTRATIONKEY>
         <WORKFLOWACTIVITY>OFAC</WORKFLOWACTIVITY>
         <WORKFLOWACTIVITYKEY>7</WORKFLOWACTIVITYKEY>
         <WORKFLOWACTIVITYSTATUSKEY>2</WORKFLOWACTIVITYSTATUSKEY>
         <WORKFLOWACTIVITYSTATUS>Passed</WORKFLOWACTIVITYSTATUS>
         <WORKFLOWPROCESSKEY>1</WORKFLOWPROCESSKEY>
         <WORKFLOWPROCESSSTATUSKEY>1</WORKFLOWPROCESSSTATUSKEY>
         <WORKFLOWPROCESS>CardAccountRegistration</WORKFLOWPROCESS>
         <WORKFLOWPROCESSSTATUS>Started</WORKFLOWPROCESSSTATUS>
         <CREATEDATE>2016-12-29 15:29:25.613</CREATEDATE>
      </Row>
   </ResultSet>
</Results>"""

def getSiblingData = { data, elementName, elementValue, siblingName ->
    def parsedResult = new XmlSlurper().parseText(data)
    (parsedResult.'**'.findAll {it.name() == elementName && it.text()== elementValue }*.parent()."$siblingName").collect{ it.text() }
}

assert ['Customer Identity Validation (CIP)'] == getSiblingData(results, 'WORKFLOWACTIVITYSTATUS', 'Failed', 'WORKFLOWACTIVITY')

# Robot​ ​part​ ​inventory

The AmazingS R&D department wants to see if AmazingS-ers can order and assemble
robots that they can control using our platform. The first step is to set up a catalogue
of the robot parts and a compatibility mapping. To get started, we’ll set up a web
service for the inventory of the robot parts.

Each robot part has a name, serial number, manufacturer, weight and a list of other
components it is compatible with within the system.
The service has the following endpoints:

- add
- read
- update
- delete
- list all parts
- list X unique robot parts that are compatible

We also need a very simple client that can hook up to the web service.
What to do:
1. Give a simple sketch of the solution
2. Outline database model
3. Implement the web service
4. Implement the Client
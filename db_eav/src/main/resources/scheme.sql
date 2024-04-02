CREATE TABLE Entity (
                        EntityID INT PRIMARY KEY,
                        EntityName VARCHAR(255)
);

CREATE TABLE Attribute (
                           AttributeID INT PRIMARY KEY,
                           AttributeName VARCHAR(255)
);

CREATE TABLE Value (
                       EntityID INT,
                       AttributeID INT,
                       Value VARCHAR(255),
                       FOREIGN KEY (EntityID) REFERENCES Entity(EntityID),
                       FOREIGN KEY (AttributeID) REFERENCES Attribute(AttributeID),
                       PRIMARY KEY (EntityID, AttributeID)
);

INSERT INTO Entity (EntityID, EntityName) VALUES (1, 'Entity1');
INSERT INTO Entity (EntityID, EntityName) VALUES (2, 'Entity2');

INSERT INTO Attribute (AttributeID, AttributeName) VALUES (1, 'Attribute1');
INSERT INTO Attribute (AttributeID, AttributeName) VALUES (2, 'Attribute2');

INSERT INTO Value (EntityID, AttributeID, Value) VALUES (1, 1, 'Value1');
INSERT INTO Value (EntityID, AttributeID, Value) VALUES (1, 2, 'Value2');
INSERT INTO Value (EntityID, AttributeID, Value) VALUES (2, 1, 'Value3');
INSERT INTO Value (EntityID, AttributeID, Value) VALUES (2, 2, 'Value4');
